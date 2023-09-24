const path = require('path');
// const HtmlWebpackPlugin = require('html-webpack-plugin/index');


// oggetto configuraione comune
var basicConfig = {
    // Aggiungere configurazione comune a più bundles
    mode: 'development',
    module: {
        rules: [
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource',
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource',
            },
        ],

    },
};

// oggetto configurazione dell'applicazione
var componentConfig = Object.assign({}, basicConfig, {
    name: "index",
    entry: {
        index: './assets/components/index/index',
        prova: './assets/components/prova/prova',
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, '../main/webapp/resources/js'),
        clean: true,
    },
});

// Return Array delle configurazioni
module.exports = [
    componentConfig,
];
