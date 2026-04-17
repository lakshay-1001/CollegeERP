export function getToken() {
  return localStorage.getItem("token");
}

// Decode JWT (simple version)
export function parseJwt(token: string) {
  try {
    return JSON.parse(atob(token.split(".")[1]));
  } catch (e) {
    return null;
  }
}

export function getUserRole() {
  return localStorage.getItem("role");
}