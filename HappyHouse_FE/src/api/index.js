import { API_BASE_URL } from "@/config";
import axios from "axios";
import router from "@/router/index";
import store from "@/store/index";

function createInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-Type": "application/json",
    },
  });
  return instance;
}

export const instance = createInstance();
instance.interceptors.request.use(
  function (config) {
    console.log("instance", store.state.token);
    return config;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  (response) => {
    console.log("instance here");
    console.log(response);
    console.log(response.request.responseURL);
    return response;
  },
  (error) => {
    console.log("instance error", error);

    if (error.response.data == "fail") {
      alert("중복된 이메일입니다.");
    } else {
      alert(error.response.data.message);
      if (error.response.data.code == 401) {
        store.state.user = false;
      }
    }
    return Promise.reject(error);
  }
);

function createInstanceWithAuth() {
  const instanceWithAuth = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-Type": "application/json",
    },
  });
  return instanceWithAuth;
}
export const instanceWithAuth = createInstanceWithAuth();

instanceWithAuth.interceptors.request.use(
  function (config) {
    console.log("auth instance", store.state.token);
    if (store.state.token == null) {
      alert("로그인 후 이용가능합니다.");
      router.push({ name: "login" });
      return;
    }
    config.headers.token = store.state.token;

    return config;
  },
  function (error) {
    alert(error);
    return Promise.reject(error);
  }
);

instanceWithAuth.interceptors.response.eject();

instanceWithAuth.interceptors.response.use(
  (response) => {
    console.log("instance auth reponse", response);
    console.log(response.request.responseURL);
    return response;
  },
  (error) => {
    console.log("inst auth error", error);
    const config = error.config;
    console.log(config);
    if (!config.url.includes("/user/login")) {
      console.log("auth not login");
      instanceWithAuth
        .post("/user/refreshToken", {
          refreshIdx: store.state.tokenIndex,
        })
        .then((data) => {
          console.log("auth data", data);
          instanceWithAuth.defaults.headers.common[
            "token"
          ] = `${data.data.result.accessToken}`;
          alert(data.data.result.message);

          if (data.data.result.code == 401) {
            store.state.user = false;
          }

          router.push({ name: "login" });
        })
        .catch((e) => {
          console.log(e);
        });
    }

    return Promise.reject(error);
  }
);
