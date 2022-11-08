package TestCallBack

interface Greeeter {
    (message: string): void;
}
function sayHi(callback: Greeeter) {
    callback('Hi')
}
function main(){
    sayHi()
}