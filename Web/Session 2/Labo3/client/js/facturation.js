let cheminEntree = "../images/entrees/";
let cheminRepas = "../images/repas/";

const tabEntree = menu.entree;
const tabRepas = menu.repas;


let initialiser = () => {
    document.getElementById('imgEntree').src = cheminEntree+menu.entree[0].image;
    document.getElementById('nomEntree').innerHTML = menu.entree[0].nom;
    document.getElementById('prixEntree').innerHTML = menu.entree[0].prix+"$";
    
    document.getElementById('imgRepas').src = cheminRepas+menu.repas[0].image;
    document.getElementById('nomRepas').innerHTML = menu.repas[0].nom;
    document.getElementById('prixRepas').innerHTML = menu.repas[0].prix+"$";

    //Générer le contenu du select pour les entree
    let selEntree = document.getElementById('selEntree');
    for(let unEntree of tabEntree){
        selEntree.options[selEntree.options.length] = new Option(unEntree.nom);
    }
    //Générer le contenu du select pour les papeteries
    let selRepas = document.getElementById('selRepas');
    for(let unRepas of tabRepas){
        selRepas.options[selRepas.options.length] = new Option(unRepas.nom);
    }
}

choixEntree = () => {
    let selEntree = document.getElementById('selEntree');
    document.getElementById('imgEntree').src = cheminEntree+menu.entree[selEntree.selectedIndex].image;
    document.getElementById('nomEntree').innerHTML = menu.entree[selEntree.selectedIndex].nom;
    document.getElementById('prixEntree').innerHTML = menu.entree[selEntree.selectedIndex].prix+"$";
}

choixRepas = () => {
    let selRepas = document.getElementById('selRepas');
    document.getElementById('imgRepas').src = cheminRepas+menu.repas[selRepas.selectedIndex].image;
    document.getElementById('nomRepas').innerHTML = menu.repas[selRepas.selectedIndex].nom;
    document.getElementById('prixRepas').innerHTML = menu.repas[selRepas.selectedIndex].prix+"$";
}

selectionEntree = () =>{
    let selEntree = document.getElementById('selEntree');
    entreeChoisit = menu.entree[selEntree.selectedIndex]
    return entreeChoisit;
}

selectionRepas = () =>{
    let selRepas = document.getElementById('selRepas');
    repasChoisit = menu.repas[selRepas.selectedIndex]
    return repasChoisit;
}

calculTPS = () => {
    let entree = selectionEntree();
    let repas = selectionRepas();
    let tps = (entree.prix + repas.prix) * 0.05;
    return tps
}

calculTVQ = () => {
    let entree = selectionEntree();
    let repas = selectionRepas();
    let tvq = (entree.prix + repas.prix) * 0.09975;
    return tvq
}

calculTTL = () => {
    let entree = selectionEntree();
    let repas = selectionRepas();
    let tps = calculTPS();
    let tvq = calculTVQ();
    let total = entree.prix + repas.prix + tps + tvq;
    return total
}

facture = () =>{
    let soustotal = (selectionEntree().prix + selectionRepas().prix);
    soustotal= soustotal;
    tps = calculTPS();
    tvq = calculTVQ();
    let taxes = tps + tvq;
    let total = calculTTL();
    document.getElementById('containerTable').innerHTML = `
    <div id="calcTable">
    <table>
    <th>Facture</th>
    <tr>
      <td id="calcSousTtl">Sous-total: ${soustotal.toFixed(2)}$</td>
    </tr>
    <tr>
      <td id="calcTPS">TPS: ${tps.toFixed(2)}$</td>
    </tr>
    <tr>
      <td id="calcTVQ">TVQ: ${tvq.toFixed(2)}$</td>
    </tr>
    <tr>
      <td id="calcTaxes">Taxes: ${taxes.toFixed(2)}$</td>
    </tr>
    <tr>
      <td id="clacTtl">Total: ${total.toFixed(2)}$</td>
    </tr>
    <tr><td class = "msg">Bonne appétit!</td></tr>
  </table>
  </div>
    `
}