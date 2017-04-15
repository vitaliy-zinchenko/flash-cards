'use strict';

// Parsing command line arguments
var argv = require('minimist')(process.argv.slice(1));
console.log("Command: " + JSON.stringify(argv));

// Defaults variables
var MODE = argv.mode || 'development';
var DEV_PORT = argv.dev_port || 9999;
var PROXY_TARGET = argv.proxy_target || 'http://fc-dev-env-3.us-west-2.elasticbeanstalk.com/';

console.log("Running Webpack with MODE=" + MODE);

var webpack = require('webpack');
var AppCacheWebpackPlugin = require('appcache-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var CleanWebpackPlugin = require('clean-webpack-plugin');
var path  = require('path');

var srcPath     = path.join(__dirname, 'app-fc');
var dstHomePath = path.join(__dirname, '/../../public');
var dstPath     = path.join(dstHomePath, 'ui');

module.exports = {
    context: srcPath,
    entry: path.join(srcPath, 'app.js'),
    output: {
      path:  dstPath,
      filename: "/app.js",
      library: "home"
    },
    watch: MODE == 'development',
    devtool: MODE == 'development' ? "source-map" : null,
    module: {
      preLoaders: [
        {
          test: /\.js$/,
          loader: "source-map-loader"
        }
      ],
      loaders: [
        { test: /\.css$/, loader: "style-loader!css-loader" },
        { test: /\.appcache$/, loader: "file" },
        { test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: "file" },
        { test: /\.(woff|woff2)$/, loader:"url?prefix=font/&limit=5000" },
        { test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&mimetype=application/octet-stream" },
        { test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&mimetype=image/svg+xml" },
        { test: /\.html$/, loader: "html-loader" }
      ]
    },
    plugins: [
        new CleanWebpackPlugin(['ui'], {
          root: dstHomePath,
          verbose: true,
          dry: false
        }),
        new webpack.NoErrorsPlugin(),
        new HtmlWebpackPlugin({
          inject  : true,
          hash    : true,
          template: 'index.html'
        }),
        new AppCacheWebpackPlugin({
          network: ["*"],
          output: 'manifest.appcache'
        })
    ],
    devServer: {
      host: 'localhost',
      port : DEV_PORT,
      contentBase: __dirname + "/app-fc",
      proxy: [{
          path: '/api',
          target: PROXY_TARGET,
          secure: false,
          changeOrigin: true
        }]
    }
};


if (MODE == 'production') {
  module.exports.plugins.push(
    new webpack.optimize.UglifyJsPlugin({
      compress: {
        // don't show unreachable variables etc
        warnings:     false,
        drop_console: true,
        unused:       true,
        dead_code:    true
      }
    })
  );
}