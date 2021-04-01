import axios from "axios";
import React, { useState } from "react";
import { apiDefault } from "../../lib/client";

const Fd = () => {
    const [img, setImage] = useState(null);

    const onChange = (e) => {
        const image = e.target.files[0];
        setImage(image);
        console.log(img);
    };

    const onClick = async () => {
        console.log(img);
        const formData = new FormData();
        formData.append("multipartFile", img);
        console.log(formData);
        const res = await apiDefault().post(
            "/test/uploadTest01",

            formData,

            {
                headers: {
                    "content-type": "multipart/form-data",
                },
            }
        );
        console.log(res);
    };

    return (
        <div>
            <input
                type="file"
                id="file"
                name="file"
                multiple="multiple"
                onChange={onChange}
            />
            <button onClick={onClick}>button</button>
        </div>
    );
};

export default Fd;

<input />;
