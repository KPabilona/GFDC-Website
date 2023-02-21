const menu = document.querySelector('#mobile-menu');
const menuLinks = document.querySelector('.nav-menu');

menu.addEventListener('click', function() {
    menu.classList.toggle('is-active');
    menuLinks.classList.toggle('active');
})

var swiper = new Swiper(".mySwiper", {
    slidesPerView: 1,
    grabCursor: true,
    loop: true,
pagination: {
    el: ".swiper-pagination",
    clickable: true,
    },
navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
    },
});

// const navbar = document.querySelector("nav");
// window.addEventListener ("scroll", function() {
//     nav.classList.toggle ("sticky", window.scrollY > 100);
// });