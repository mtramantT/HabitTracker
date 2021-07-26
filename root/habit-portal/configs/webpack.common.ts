import webpack from 'webpack';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import WebpackDevServer from 'webpack-dev-server';
import * as path from 'path';


declare module 'webpack' {
    interface Configuration {
        /**
         * Can be used to configure the behaviour of webpack-dev-server when
         * the webpack config is passed to webpack-dev-server CLI.
         */
        devServer?: WebpackDevServer.Configuration | undefined;
    }
}

const config: webpack.Configuration = {
    mode: 'development',
    context: path.resolve(__dirname, '../src'),
    entry: {
        app: './index.tsx',
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, '../dist'),
        clean: true,
    },
    resolve: {
        extensions: ['.ts', '.tsx', '.jsx', '.js']
    },
    module: {
        rules: [
            {
                test: /\.(ts|js)x?$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react', '@babel/preset-typescript'],
                    },
                },
            },
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
            },
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './index.html'
        })
    ],
    devtool: "source-map",
    devServer: {
        port: 4000,
        historyApiFallback: true,
        publicPath: '/',
    },

}

export default config;
