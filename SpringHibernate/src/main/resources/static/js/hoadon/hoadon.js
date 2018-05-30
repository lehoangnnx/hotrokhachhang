function deleteOne(id) {
	$("#arrayId").val(id);
	$("#command").attr("action", contextPath + "/admin/hoadon");
};

function deletesOne(id) {
    $("#idds").val(id);

};
$(document).ready(function() {
    var a = '123.456.789,.  123';
    var b = '123,456,789  123123';

    var a1 = a.replace(/\.|,|\s/g,'');
    var b1 = b.replace(/\.|,|\s/g,'');
    console.log(a1);
    console.log(b1);
    // Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
    $("#formThongKe").validate({
        rules : {
            tungay : {
                required : true

            },
            denngay : {
                required : true

            }

        },
        messages : {
            tungay : {
                required : "* Vui Lòng Nhập Từ Ngày"

            },
            denngay : {
                required : "* Vui Lòng Nhập Đến Ngày"

            }

        }
    });
});
function parseDate(str) {
    var mdy = str.split('/');
    return new Date(mdy[2], mdy[1], mdy[0]);
};
function checkNgay() {
    var tungay = parseDate($('#tungay').val()).getTime();
    var denngay = parseDate($('#denngay').val()).getTime();

    if (denngay < tungay) {

        $('#denngay-error').css("display", "block");
        $('#denngay-error').text("* Ngày Kết Thúc Lớn Hơn Ngày Bắt Đầu");
        $('#btn-submit').attr('type', 'button');
    } else {
        $('#denngay-error').css("display", "none");
        $('#denngay-error').text("");
        $('#btn-submit').attr('type', 'submit');
    }
}