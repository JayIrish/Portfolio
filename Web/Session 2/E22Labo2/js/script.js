


function choixAutres(){
    let sel = document.getElementById('idInterets');  
    let optionChoisie = sel.options[sel.selectedIndex].value;
    let iframe = document.getElementById('iframeautre');

    if (optionChoisie == "Voyage") {
        iframe.src = "../../images/StephVoyage.jpg";
    } 
        else if (optionChoisie == "JeuxSociete") { 
           iframe.src = "../../images/JeuxSociete.jpg"; 
        }
        else if (optionChoisie == "Comiccon") { 
            iframe.src = "https://www.montrealcomiccon.com/"; 
         }
        else if (optionChoisie == "JeuxVideos") { 
              iframe.src = "https://www.youtube.com/embed/rhto4-81VL0"; 
             }
}
