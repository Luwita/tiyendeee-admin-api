class TokenService {
  getLocalRefreshToken() {
    return localStorage.getItem("refreshToken");
  }

  getLocalAccessToken() {
    return localStorage.getItem("accessToken");
  }

  updateLocalAccessToken(accessToken) {
    localStorage.setItem("accessToken", accessToken);
  }
  updateLocalRefreshToken(refreshToken) {
    localStorage.setItem("refreshToken", refreshToken);
  }

  getUser() {
    return JSON.parse(localStorage.getItem("user"));
  }
  setUser(user) {
    // console.log(JSON.stringify(user));
    localStorage.setItem("user", JSON.stringify(user));
  }
  setRole(role) {
    localStorage.setItem("role", role);
  }
  getRole() {
    return localStorage.getItem("role");
  }
  setLocalRefreshToken(refreshToken) {
    localStorage.setItem("refreshToken", refreshToken);
  }
  setLocalAccessToken(accessToken) {
    localStorage.setItem("accessToken", accessToken);
  }
  removeUser() {
    localStorage.removeItem("user");
  }
  removeRole() {
    localStorage.removeItem("role");
  }
  removeAccessToken() {
    localStorage.removeItem("accessToken");
  }
  removeRefreshToken() {
    localStorage.removeItem("refreshToken");
  }
}

export default new TokenService();
