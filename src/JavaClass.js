const java = require("./jproc");
const DATA_DIVIDE_CHARACTER = "\u0000";

module.exports = class JavaClass {
  constructor() {
    return new Proxy(this, {
      get(target, prop) {
        return (...args) => java.stdin.write([
          java.types.indexOf(target.constructor),
          prop,
          ...JavaClass.FORMAT_ARGS(args)
        ].join(DATA_DIVIDE_CHARACTER) + "\r")
      }
    })
  }

  static FORMAT_ARGS(args) {
    // format...
    return args.map(a => JSON.stringify(a));
  }
}