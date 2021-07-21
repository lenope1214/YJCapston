import { apiDefault } from "../client";

export const postLogin = (id, pw) => {
    return apiDefault().post("/login", {
        id,
        password: pw,
    });
};
export const postSNSLogin = (profile_id) => {
    return apiDefault().post("/oauth/login", {
        provider: 'google',
        providerId: profile_id,
    });
};
