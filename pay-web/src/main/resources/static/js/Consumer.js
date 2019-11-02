layui.use(['table', 'layer','jquery'], function(){  //如果只加载一个模块，可以不填数组。如：layui.use('form')
    var table = layui.table //获取table模块
        ,layer = layui.layer //获取layer模块
        ,$ =layui.jquery;

    var url = "";
    var qrcode = new createQRCode(document.querySelector("#pay-load"), url, 256, 256);

    $(document).on("click","#paysure",function(){
        $.ajax({
            url:'/trade/precreate',
            dataType:'text',
            type:'get',
            success:function (res) {
                qrcode.show();
                qrcode.change(res);
                layer.open({
                    type: 1,
                    content: $('#pay-load')
                });

            }
        });
    });

});