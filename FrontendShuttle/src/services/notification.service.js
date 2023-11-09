import useAuthApi from "../helpers/authHook";

export const notificationService = {
  create,
  deleteNotification,
  getAll,
  tranform,
  updateStatus,
};

async function updateStatus(id, Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.patch("notifications/" + id, Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function getAll(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.get("notifications/search", {
      params: Objparams,
    });
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function create(Objparams) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.post("notifications", Objparams);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

async function deleteNotification(id) {
  try {
    const privateAuth = useAuthApi();
    const response = await privateAuth.delete("notifications/" + id);
    return response.data;
  } catch (e) {
    return e.response;
  }
}

function tranform(rows) {
  const selectableItems = [];
  let i = 1;
  rows.forEach((item) => {
    selectableItems.push({
      id: i++,
      to: item.to,
      user_type: item.user_type,
      days: item.days,
      time: item.time,
      schedule: item.schedule,
      notification_title: item.notification.title,
      notification_body: item.notification.body,
      notification_picture: item.notification.picture,
      createdAt: item.createdAt,
    });
  });
  return selectableItems;
}
