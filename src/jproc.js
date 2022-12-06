const { exec } = require("child_process");

const java = exec("java src/Main.java");
java.stderr.pipe(process.stderr);

module.exports = java;
module.exports.types = [
  String,
  Number,
  Boolean,
  Number,
]