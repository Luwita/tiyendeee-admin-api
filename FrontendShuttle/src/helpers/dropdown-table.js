const bootstrap4DropDown = () => {
  this.$el
    .querySelectorAll('[data-toggle="dropdown"]')
    .forEach(($dropdownToggle) => {
      const $dropdown = $dropdownToggle.nextElementSibling;
      let isShown = false;

      function setIsShown(state) {
        isShown = state;
        $dropdown.classList.toggle("show", isShown);
      }

      if (!this.dropdowns.includes($dropdown)) {
        this.dropdowns.push($dropdown);

        $dropdownToggle.addEventListener("click", (event) => {
          event.preventDefault();
          setIsShown(!isShown);
        });

        $dropdown.addEventListener("click", (event) => {
          event.preventDefault();
          setIsShown(false);
        });

        $dropdown.clickOutsideEvent = (event) => {
          const isDropdownOrChildren =
            $dropdown === event.target || $dropdown.contains(event.target);
          const isDropdownToggleOrChildren =
            $dropdownToggle === event.target ||
            $dropdownToggle.contains(event.target);

          if (!isDropdownOrChildren && !isDropdownToggleOrChildren) {
            setIsShown(false);
          }
        };
        document.addEventListener("click", $dropdown.clickOutsideEvent);
      }
    });
};

export default {
  enableBootstrap4DropDown,
};
