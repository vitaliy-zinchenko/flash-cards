'use strict';

const NODE_ENV = process.env.NODE_ENV || 'development'; //TODO: 'NODE_ENV' is not recognized as an internal or external command...
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const path  = require('path');

const srcPath    = path.join(__dirname, 'app-fc');
const dstHomePath    = path.join(__dirname, '/../resources');
const dstPath    = path.join(dstHomePath, '/static');

console.log("Running Webpack with NODE_ENV=" + NODE_ENV)

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
    port : 9000,
    contentBase: __dirname + "/app-fc",
    proxy: [{
        path: /api/,
        target: 'http://104.236.116.198:9000',
        secure: false,
        changeOrigin: true
      }]
  }

};

if (NODE_ENV == 'production') {  //TODO: not work yet. Need fixed 'NODE_ENV'

  module.exports.plugins.push(
    new webpack.optimize.UglifyJsPlugin({
      compress: {
        // don't show unreachable variables etc
        warnings:     false,
        drop_console: true,
        unsafe:       true
      }
    })
  );
}

// TODO: minification for production