import { getCurrentInstance } from "vue";

export function useToast() {
  const root = getCurrentInstance();
  return root.proxy.$toast;
}

export function useSwal() {
  const root = getCurrentInstance();
  return root.proxy.$swal;
}

export function useBVModal() {
  const root = getCurrentInstance();
  return root.proxy.$bvModal;
}

export default { useToast, useSwal, useBVModal };
