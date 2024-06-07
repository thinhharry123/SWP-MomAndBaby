
let chartArea = "";
const statisOrderStaff = function () {
    chartArea = Morris.Bar({
        element: 'status-bill',
        xkey: 'y',
        ykeys: ['cancel', 'new', "accept", 'prepare', 'delivery', 'finish'],
        labels: ['Cancel', 'New', "Accept", 'Prepare', 'Delivery', 'Finish'],
        xLabelAngle: 60
    });
    filterStatisticBill();
}

const  filterStatisticBill = (from = false, to = false) => {
    let fromValue = "";
    let toValue = "";
    if (from && to) {
        fromValue = document.querySelector(from).value;
        toValue = document.querySelector(to).value;
    }
    var url = '/MomAndBaby/staff/statistic';
    $.ajax({
        type: 'Post',
        url: url,
        data: {
            type: "statusBill",
            from: fromValue,
            to: toValue
        },
        dataType: 'json',
        success: function (data) {
            let resultArray = [];
            data.forEach(item => {
                const date = item.date;
                const status = item.status;
                let dateObject = resultArray.find(obj => obj.date === date);
                if (!dateObject) {
                    dateObject = {date: date, counts: {}};
                    resultArray.push(dateObject);
                }
                if (!dateObject.counts[status]) {
                    dateObject.counts[status] = 1;
                } else {
                    dateObject.counts[status]++;
                }
            });
            let morrisData = [];
            resultArray.forEach(dateObject => {
                const date = dateObject.date;
                const counts = dateObject.counts;
                let morrisItem = {y: date};
                for (let status in counts) {
                    let label = "";
                    switch (status) {
                        case "0":
                            label = "cancel";
                            break;
                        case "1":
                            label = "new";
                            break;
                        case "2":
                            label = "accept";
                            break;
                        case "3":
                            label = "prepare";
                            break;
                        case "4":
                            label = "delivery";
                            break;
                        case "5":
                            label = "finish";
                            break;
                    }
                    morrisItem[label] = counts[status];
                }

                morrisData.push(morrisItem);
            });
            console.log(morrisData);
            if (morrisData.length == 0) {
                morrisData = [
                    {
                        y: "",
                        cancel: 0,
                        new : 0,
                        accpet: 0,
                        prepare: 0,
                        delivery: 0,
                        finish: 0
                    }
                ];
            }
            chartArea.setData(morrisData);
        },
        error: function (data) {
            console.log(data);
        }
    });
}



