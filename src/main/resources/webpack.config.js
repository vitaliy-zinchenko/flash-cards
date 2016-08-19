'use strict';

const NODE_ENV = process.env.NODE_ENV || 'development'; //TODO: 'NODE_ENV' is not recognized as an internal or external command...
const webpack = require('webpack');


module.exports = {
  entry: "./app",
  output: {
    path: __dirname + "/static/js",
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
    new webpack.NoErrorsPlugin(),
  ],

  devServer: {
    host: 'localhost',
    port : 8080,
    proxy: {
      "*": 'http://localhost:9000/'
    }
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