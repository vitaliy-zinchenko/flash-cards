module.exports = {
  entry: "./app",
  output: {
    path: __dirname + "/static/js",
    filename: "app.js",
    library: "home"
  },

  watch: true,
  devtool: "source-map"
};