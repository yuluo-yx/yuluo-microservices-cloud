import service from './manager.ts'

// 封装get  post 方法
export function get(url: string, params: any) {
    return new Promise((resolve, reject) => {
        service.get(url, {
            params: params,
            // headers: axios.defaults.headers
        }).then(res => {
            resolve(res.data);
        }).catch(err => {
            reject(err.data);
        });
    });
}

export function del(url: string, params: any) {
    return new Promise((resolve, reject) => {
        service.delete(url, {
            params: params,
            // headers: axios.defaults.headers
        }).then(res => {
            resolve(res.data);
        }).catch(err => {
            reject(err.data);
        });
    });
}

export function put(url: string, data: any) {
    return new Promise((resolve, reject) => {
        service.put(url, {
            data: data,
            // headers: axios.defaults.headers
        }).then(res => {
            resolve(res.data);
        }).catch(err => {
            reject(err.data);
        });
    });
}

// export function download(url: string, params: any) {
//     return new Promise((resolve, reject) => {
//         service.get(url, {
//             params: params,
//             // headers: axios.defaults.headers
//             responseType: 'blob'
//         }).then(res => {
//             resolve(res.data);
//         }).catch(err => {
//             reject(err.data);
//         });
//     });
// }

// 通用下载方法
export function download(url: string, params: any) {
    return new Promise((resolve, reject) => {
        service.post(url, params, {
            params: params,
            responseType: 'blob',
        }).then(res => {
            resolve(res.data);
        }).catch(err => {
            reject(err.data);
        });
    })
}

export function post(url: string, params: any) {
    return new Promise((resolve, reject) => {
        service.post(url, params, {
            // headers: axios.defaults.headers
        })
            .then(res => {
                resolve(res.data);
            })
            .catch(err => {
                reject(err.data);
            });
    });
}
