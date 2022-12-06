const java = require("./jproc");
java.stdout.pipe(process.stdout);

const JavaClass = require("./JavaClass");

class JFrame extends JavaClass {}

java.types.push(
  JFrame
)
module.exports = {
  JFrame
}