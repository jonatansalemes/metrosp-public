const messages = {
    api: {
        error: {
            title: 'Erro de comunicação com o servidor',
            message: 'Houve algum problema de comunicação com o servidor. Tente novamente mais tarde ou dentro de alguns instantes.'
        }
    },
    format: (str: string, ...val: any[]) => {
        for (let index = 0; index < val.length; index++) {
            str = str.replace(`{${index}}`, String(val[index]));
        }
        return str;
    }
}

export default messages;