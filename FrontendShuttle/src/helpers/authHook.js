import { authApi } from "./auth";
import { useAuth } from "../store/useAuth";
import { refreshAuth } from "../store/refToken";

const useAuthapi = () => {
  const auth = useAuth();
  const refreshState = refreshAuth();
  authApi.interceptors.response.use(
    (response) => response,
    async (error) => {
      //const { config: originalRequest } = error;
      const prevReq = error?.config;
      if (error?.response?.status === 401 && !prevReq?.sent) {
        prevReq.sent = true;
        const reAuth = await refreshState.refresh();
        //  console.log("reAuth", reAuth);
        prevReq.headers["Authorization"] = `Bearer ${reAuth}`;
        return authApi(prevReq);
      }
      return Promise.reject(error);
    }
  );
  authApi.interceptors.request.use(
    (config) => {
      if (!config.headers["Authorization"]) {
        config.headers[
          "Authorization"
        ] = `Bearer ${auth.authToken.accessToken}`;
      }
      return config;
    },
    (error) => Promise.reject(error)
  );
  return authApi;
};

export default useAuthapi;
