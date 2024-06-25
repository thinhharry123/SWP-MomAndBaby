const filterAddress = (type, id) => {
    let data = [];
    if (type == '#district') {
        data = {
            province: id,
            district: ""
        };
        $("#ward").html("<option value=''>Choose  a ward</option>");
    } else if (type == '#ward') {
        data = {
            province: "",
            district: id
        };
    }
    $.ajax({
        type: 'POST1111111',
        url: '/SWP391-MomAndBaby/address-checkout',
        data: JSON.stringify(data),
        contentType: 'json',
        success: function (response) {
            $(type).html(response);
        },
        error: function (xhr, status, error) {
            console.log('Lỗi: ' + error);
        }
    });
};