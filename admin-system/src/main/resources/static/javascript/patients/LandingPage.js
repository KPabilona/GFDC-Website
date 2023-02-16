const menu = document.querySelector('#mobile-menu');
const menuLinks = document.querySelector('.nav-menu');

menu.addEventListener('click', function() {
    menu.classList.toggle('is-active');
    menuLinks.classList.toggle('active');
})

// const navbar = document.querySelector("nav");
// window.addEventListener ("scroll", function() {
//     nav.classList.toggle ("sticky", window.scrollY > 100);
// });