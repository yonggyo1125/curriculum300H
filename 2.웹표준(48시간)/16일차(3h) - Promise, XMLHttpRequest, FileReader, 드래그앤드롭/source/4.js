/**
* promise all 
*
* 이 프로그램을 실행하면 Promise.all에 지정한 모든 Promise 객체가 실행됩니다.
* 모든작업이 성공으로 끝나면 성공 콜백이 실행됩니다. 
* 실패로 끝난 Promise 객체가 하나라도 있으면 가장 먼저 실패로 끝난 Promise 객체에서 실행한 reject 함수의 인수가 실패 콜백 함수의 인수로 들어갑니다.
*/
function buyAsync(name, mymoney) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			const payment = parseInt(prompt(`${name}님, 지불하고자 하는 금액을 입력하십시오`));
			let balance = mymoney - payment;
			if (balance > 0) {
				console.log(`${payment}원을 지불했습니다.`);
				resolve(balance);
			} else {
				reject(`잔액은 ${mymoney}원 입니다. 구매할 수 없습니다.`);
			}
		}, 1000);
	});
}

Promise.all([
	buyAsync("Tom", 500),
	buyAsync("Huck", 600),
	buyAsync("Becky", 1000)
])
.then((balance) => {
	console.log(balance);
})
.catch((error) => {
	console.log(error);
});