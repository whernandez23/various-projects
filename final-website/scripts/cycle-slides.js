var slideIndex = 0;
cycleSlides();

function cycleSlides()
{
   var i;
   var slides = document.getElementById("slideshow").getElementsByTagName("img")

   for(i=0; i < slides.length; i++)
   {
    slides[i].style.display = "none";
   }

   slideIndex++;

   if(slideIndex > slides.length)
    slideIndex = 1;

   slides[slideIndex-1].style.display = "block";
   setTimeout(cycleSlides, 9000);
}