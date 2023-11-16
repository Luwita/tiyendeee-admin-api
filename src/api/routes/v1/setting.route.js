const express = require('express');
const controller = require('../../controllers/setting.controller');

const {
  authorize,
  ADMIN,
  getAuth,
} = require('../../middlewares/auth');

const router = express.Router();


router
  .route('/')
  .post(getAuth('manage.application.settings', 'master.admin'), controller.create);

router
  .route('/:type')
  .get(getAuth('manage.application.settings', 'master.admin'), controller.get);

router
  .route('/:settingId')
  /**
  * update the single location
  * */
  .patch(getAuth('application.settings.edit', 'master.admin'), controller.update);


module.exports = router;
