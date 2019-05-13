
console.log("欢迎来到zip的小窝 🍓🍒🍎🍉🍑🍊🥭🍍🍌🌶️🍅🥥🍇🥝🍐🍏🍈🍋🌰 ");

let go = (event)=>{
    let tr = event.target;
    let a = tr.getElementsByTagName('a')[0];
    //alert(a.innerText)
    //console.log(a.getAttribute('href'));
    window.open(a.getAttribute('href'),'_self');
};


let tr = document.getElementsByTagName("tr");
for(let i = 0;i<tr.length;i++){
    tr[i].onclick = go;
}
