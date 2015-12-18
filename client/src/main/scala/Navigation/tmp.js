app.controller('paralaxCtrl', function($timeout, $rootScope){
    var elements = document.getElementsByClassName('paralax');
    var positionElement = [];
    var pelement = [];
    var panimation;
    var classToRemove;
    var subElement;
    var subPanimation;
    var del;
    var rem;
    var loop;
    var lastSubElement;
    var subTmp = [];
    var autonext;
    var sbreak = [];
    var scrolling = false;

    $rootScope.$on('$locationChangeSuccess', function() {
        goTop()
    });

    goTop();

    function initialize() {
        var i = 0;
        for (i; i < elements.length; i++) {
            positionElement[i] = elements[i].getBoundingClientRect();
            pelement[i] = { element: elements[i], subElements: elements[i].getElementsByClassName('subElements')};
        }
    }

    function goTop () {
        window.scrollTo(0, 0);
    }

    document.addEventListener("DOMContentLoaded", function() {
        goTop();
    });

    window.onbeforeunload = goTop;
    window.onresize = initialize();
    initialize();


    function sequence (i) {
        for(j=0; j<pelement.length; j++) {
            if (j != i && pelement[j].element.getAttribute('panimation') != 'bgfixed') {
                var pan = pelement[j].element.getAttribute('panimation');
                pelement[j].element.classList.remove(pan + 'Leave');
                pelement[j].element.classList.remove(pan + 'Enter');
                for (n = 0; n < pelement[j].subElements.length; n++) {
                    subPanimation = pelement[j].subElements[n].getAttribute('panimation');
                    pelement[j].subElements[n].classList.remove(subPanimation + 'Leave');
                    pelement[j].subElements[n].classList.remove(subPanimation + 'Enter');
                }
            }
        }
        panimation = pelement[i].element.getAttribute('panimation');
        pelement[i].element.classList.remove(panimation + 'Leave');
        pelement[i].element.classList.add(panimation + 'Enter');
        lastSubElement = pelement[i].subElements[pelement[i].subElements.length-1];
        count = 1;
        if(lastSubElement != undefined && pelement[i].element.getAttribute('sequence') != 'false') {
            while (lastSubElement.getAttribute('remove') == null && lastSubElement != pelement[i].subElements[0]) {
                count = count + 1;
                lastSubElement = pelement[i].subElements[pelement[i].subElements.length - count]
            }
        }
        loop = pelement[i].element.getAttribute('loop');
        subTmp = pelement[i].subElements;
        if(pelement[i].element.getAttribute('sequence') != 'false') {
            for (n = 0; n < pelement[i].subElements.length; n++) {
                subPanimation = pelement[i].subElements[n].getAttribute('panimation');
                pelement[i].subElements[n].classList.remove(subPanimation + 'Leave');
                subElement = pelement[i].subElements[n];
                del = subElement.getAttribute('delay');
                (function (subElement, subPanimation, del) {
                    if (subElement.className.match('Enter') == null) {
                        sbreak[n] = $timeout(function () {
                            subElement.classList.add(subPanimation + 'Enter');
                        }, del);
                        if (remdel = subElement.getAttribute('remove')) {
                            $timeout(function () {
                                subElement.classList.remove(subPanimation + 'Enter');
                                subElement.classList.add(subPanimation + 'Leave');
                                if (subElement == lastSubElement && loop == 'true' && pelement[i].element.className.match('Enter')) {
                                    sequence(i);
                                }
                            }, remdel);
                        }
                    }
                })(subElement, subPanimation, del, subTmp);
            }
        }
    }
    function paralax () {
        for (i = 0; i < pelement.length; i++) {
            if ($(window).scrollTop() + 30 >= positionElement[i].top && $(window).scrollTop() -30 <= positionElement[i].top &&
                pelement[i].element.className.match("Enter") == null) {
                window.scrollTo(0, positionElement[i].top);
                sequence(i);
            } else if ($(window).scrollTop() + 100 <= positionElement[i].top && $(window).scrollTop() + 200 >= positionElement[i].top &&
                pelement[i].element.className.match("Enter") && scrolling == false) {
                for (br = 0; br<sbreak.length; br++){
                    $timeout.cancel(sbreak[br])
                }
                rem = pelement[i].element.getAttribute('panimation');
                pelement[i].element.classList.remove(rem + 'Enter');
                pelement[i].element.classList.add(rem + 'Leave');
                for (ii = 0; ii < pelement[i].subElements.length; ii++) {
                    classToRemove = pelement[i].subElements[ii].getAttribute('panimation');
                    pelement[i].subElements[ii].classList.remove(classToRemove + 'Enter');
                    pelement[i].subElements[ii].classList.add(classToRemove + 'Leave');
           }
                if (i > 0 ) {
                    w = i;
                    var topPrev = positionElement[i - 1].top;
                    scrolling = true;
                    var scrollPrev = setInterval(function(){
                        if ($(window).scrollTop() > topPrev) {
                            window.scrollTo(0, $(window).scrollTop() - 10);
                        }
                        if ($(window).scrollTop() <= topPrev + 10){
                            clearInterval(scrollPrev);
                            scrolling = false;
                        }
                    },2);
                    $timeout(function(){
                        pelement[w].element.classList.remove(panimation + 'Leave');
                        pelement[w].element.classList.remove(panimation + 'Enter');
                    }, 600);
                }
            }
            if ($(window).scrollTop() <= positionElement[i].bottom-200 && $(window).scrollTop() >= positionElement[i].bottom - 350 && i<pelement.length && pelement[i+1].element.className.match("Leave") == null && pelement[i].element.getAttribute('autonext')=="true" && scrolling == false) {
                pelement[i].element.classList.remove(panimation + 'Enter');
                var top = positionElement[i + 1].top;
                scrolling = true;
                var scroll = setInterval(function(){
                    if ($(window).scrollTop() < top) {
                        window.scrollTo(0, $(window).scrollTop() + 10);
                    }
                    if ($(window).scrollTop() >= top - 5){
                        clearInterval(scroll);
                        scrolling = false;
                    }
                },5);

                for (br = 0; br<sbreak.length; br++){
                    $timeout.cancel(sbreak[br])
                }
                for (ii = 0; ii < pelement[i].subElements.length; ii++) {
                    classToRemove = pelement[i].subElements[ii].getAttribute('panimation');
                    pelement[i].subElements[ii].classList.add(classToRemove + 'Leave');
                    pelement[i].subElements[ii].classList.remove(classToRemove + 'Enter');
                }
            }
        }
    }
    function start () {
        if ($(window).width() > 900) {
            paralax();
            document.onscroll = paralax;
        } else {
            for (i = 0; i < pelement.length; i++) {
                pelement[i].element.classList.remove('paralax');
                for (ii = 0; ii < pelement[i].subElements.length; ii++) {
                    pelement[i].subElements[ii].classList.remove('subElements');
                }
            }
        }
    }
    start();
    window.onresize = start;
});