

let arr= [5, 5, 6, 6, 7, 7, 5, 6, 7];
console.log(shuffle(arr));

function shuffle(arr){
    for(let i= 0; i< arr.length- 1; i++){
        if(i% 2== 0){
            if(arr[i]> arr[i+ 1]){
                swap(arr, i, i+ 1);
            }
        }
        else{
            if(arr[i]< arr[i+ 1]){
                swap(arr, i, i+ 1);
            }
        }
    }
    return arr;
}

function swap(arr, i, j){
    let temp= arr[i];
    arr[i]= arr[j];
    arr[j]= temp;
}