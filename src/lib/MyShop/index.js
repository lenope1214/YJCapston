import axios from "axios";

import React, { useEffect, useState } from "react";
import {apiDefault} from "../client";

export const getmyShop = () => {
    return apiDefault().get("/myShop",{
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
        },
    });
};
