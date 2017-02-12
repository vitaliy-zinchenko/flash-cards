'use strict';

// Parsing command line arguments
var argv = require('minimist')(process.argv.slice(1));
console.log("Command: " + JSON.stringify(argv));

// Defaults variables
const NODE_ENV = argv.mode || 'development';
const DEV_PORT = argv.dev_port || 9999;
const PROXY_TARGET = argv.proxy_target || 'http://flashcards-dev.xyz:9000';

const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const path  = require('path');

const srcPath     = path.join(__dirname, 'app-fc');
const dstHomePath = path.join(__dirname, '/../../public');
const dstPath     = dstHomePath;

console.log("Running Webpack with NODE_ENV=" + NODE_ENV);

module.exports = {
  entry: path.join(srcPath, 'app.js'),
  output: {
    path:  dstPath,
    filename: "app.js",
    library: "home"
  },

  watch: NODE_ENV == 'development',

  devtool: NODE_ENV == 'development' ? "source-map" : null,

  module: {
    preLoaders: [
      {
        test: /\.js$/,
        loader: "source-map-loader"
      }
    ],
    loaders: [
      {
        test: /\.js$/,
        loader: 'babel',
        query: {
          presets: ['es2015']
        },
        exclude: /node_modules/
      },

      {
        test: /\.css$/,
        loader: "style-loader!css-loader"
      },
      { test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: "file" },
      { test: /\.(woff|woff2)$/, loader:"url?prefix=font/&limit=5000" },
      { test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&mimetype=application/octet-stream" },
      { test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&mimetype=image/svg+xml" },
      {
        test: /\.html$/,
        loader: "html-loader"
      }
    ]
  },

  plugins: [
    new CleanWebpackPlugin(['static'], {
      root: dstHomePath,
      verbose: true,
      dry: false
    }),
    new webpack.NoErrorsPlugin(),
    new HtmlWebpackPlugin({
      inject  : true,
      hash    : true,
      template: 'app-fc/index.html'
    }),
    new HtmlWebpackPlugin({
      filename: 'signin.html',
      inject  : true,
      hash    : true,
      template: 'app-fc/signin.html'
    }),
    new CopyWebpackPlugin([{ from: 'app-fc/signin.js' }]) //TODO remove after loging page implementation
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

if (NODE_ENV == 'production') {
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