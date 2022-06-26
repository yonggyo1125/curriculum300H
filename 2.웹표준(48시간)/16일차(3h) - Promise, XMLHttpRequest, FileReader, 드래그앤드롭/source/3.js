/**
* async ~ await 방식 
*
*/
function buyAsync(mymoney) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			const payment = parseInt(prompt("지불하고자 하는 금액을 입력하십시오"));
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

(async function() {
	try {
		let balance = await buyAsync(500);
		console.log(`잔액은 ${balance}원 입니다.`);
		balance = await buyAsync(balance);
		console.log(`잔액은 ${balance}원 입니다.`);
		balance = await buyAsync(balance);
		console.log(`잔액은 ${balance}원 입니다.`);
	} catch (error) {
		console.log(error);
	}
})();