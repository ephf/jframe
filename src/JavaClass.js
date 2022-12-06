const java = require("./jproc");
const DATA_DIVIDE_CHARACTER = "\u0000";

module.exports = class JavaClass {
  id = -1;
  constructor() {
    java.stdin.write("-1\r");
    java.stdout.once("data", data => {
      this.id = Number(data.toString());
    });
    return new Proxy(this, {
      get(target, prop) {
        return (...args) => java.stdin.write([
          target.id,
          java.types.indexOf(target.constructor),
          prop,
          ...JavaClass.FORMAT_ARGS(args)
        ].join(DATA_DIVIDE_CHARACTER) + "\r")
      }
    })
  }

  static FORMAT_ARGS(args) {
    // format...
    return args.reduce((formatted, arg) => [...formatted, java.types.indexOf(arg.constructor), arg], []);
  }
}