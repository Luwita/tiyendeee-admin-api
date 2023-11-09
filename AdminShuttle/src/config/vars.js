const path = require("path");

// import .env variables
require("dotenv-safe").config({
  allowEmptyValues: true,
});

process.env.TZ = "Asia/Kolkata";

//console.log("FULLBASEURL", `${JSON.stringify(process.env)}`);
module.exports = {
  env: process.env.NODE_ENV,
  port: process.env.PORT,
  BASEURL: process.env.BASE_URL,
  FULLBASEURL: `${process.env.FULL_BASEURL}`,
  jwtSecret: process.env.JWT_SECRET,
  jwtExpirationInterval: process.env.JWT_EXPIRATION_HOURS,
  mongo: {
    uri:
      process.env.NODE_ENV === "test"
        ? process.env.MONGO_URI_TESTS
        : process.env.MONGO_URI,
  },
  logs: process.env.NODE_ENV === "production" ? "combined" : "dev",
};
