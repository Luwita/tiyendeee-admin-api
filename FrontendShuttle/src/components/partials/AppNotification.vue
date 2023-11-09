<template>
    <div>
        <b-nav-item-dropdown right class="preview-list">
          <template slot="button-content">
            <div class="nav-link count-indicator">
              <i class="mdi mdi-bell-outline"></i>
              <span class="count">7</span>
            </div>
          </template>
          <b-dropdown-item class="preview-item py-2 px-0 border-bottom">
            <p class="mb-0 font-weight-medium float-left">
              You have 7 unread mails
            </p>
            <span class="badge badge-pill badge-primary float-right"
              >View all</span
            >
          </b-dropdown-item>
          <b-dropdown-item class="preview-item">
            <div class="preview-thumbnail">
              <img
                src="../../assets/images/faces/face10.jpg"
                alt="image"
                class="img-sm profile-pic"
              />
            </div>
            <div class="preview-item-content flex-grow py-2">
              <p class="preview-subject ellipsis font-weight-medium text-dark">
                Marian Garner
              </p>
              <p class="font-weight-light small-text">
                The meeting is cancelled
              </p>
            </div>
          </b-dropdown-item>
          <b-dropdown-item class="preview-item">
            <div class="preview-thumbnail">
              <img
                src="../../assets/images/faces/face12.jpg"
                alt="image"
                class="img-sm profile-pic"
              />
            </div>
            <div class="preview-item-content flex-grow py-2">
              <p class="preview-subject ellipsis font-weight-medium text-dark">
                David Grey
              </p>
              <p class="font-weight-light small-text">
                The meeting is cancelled
              </p>
            </div>
          </b-dropdown-item>
          <b-dropdown-item class="preview-item">
            <div class="preview-thumbnail">
              <img
                src="../../assets/images/faces/face1.jpg"
                alt="image"
                class="img-sm profile-pic"
              />
            </div>
            <div class="preview-item-content flex-grow py-2">
              <p class="preview-subject ellipsis font-weight-medium text-dark">
                Travis Jenkins
              </p>
              <p class="font-weight-light small-text">
                The meeting is cancelled
              </p>
            </div>
          </b-dropdown-item>
        </b-nav-item-dropdown>
         <b-nav-item-dropdown right class="preview-list">
          <template slot="button-content">
            <div class="nav-link count-indicator">
              <i class="mdi mdi-email-outline"></i>
              <span class="count bg-success">4</span>
            </div>
          </template>
          <b-dropdown-item class="preview-item py-2 px-0 border-bottom">
            <p class="mb-0 font-weight-medium float-left">
              You have 4 new notifications
            </p>
            <span class="badge badge-pill badge-primary ml-auto">View all</span>
          </b-dropdown-item>
          <b-dropdown-item class="preview-item">
            <div class="preview-thumbnail">
              <i class="mdi mdi-alert m-auto text-primary"></i>
            </div>
            <div class="preview-item-content flex-grow py-2">
              <h6 class="preview-subject font-weight-normal text-dark mb-1">
                Application Error
              </h6>
              <p class="font-weight-light small-text mb-0">Just now</p>
            </div>
          </b-dropdown-item>
          <b-dropdown-item class="preview-item">
            <div class="preview-thumbnail">
              <i class="mdi mdi-settings m-auto text-primary"></i>
            </div>
            <div class="preview-item-content flex-grow py-2">
              <h6 class="preview-subject font-weight-normal text-dark mb-1">
                Settings
              </h6>
              <p class="font-weight-light small-text mb-0">Private message</p>
            </div>
          </b-dropdown-item>
          <b-dropdown-item class="preview-item">
            <div class="preview-thumbnail">
              <i class="mdi mdi-airballoon m-auto text-primary"></i>
            </div>
            <div class="preview-item-content flex-grow py-2">
              <h6 class="preview-subject font-weight-normal text-dark mb-1">
                New user registration
              </h6>
              <p class="font-weight-light small-text mb-0">2 days ago</p>
            </div>
          </b-dropdown-item>
        </b-nav-item-dropdown>
    </div>
</template>

<script>
export default {
name: 'app-notification',
}
</script>

<style>

</style>