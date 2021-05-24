let main = {
    init: function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        let data = {
            labelTitle: $('#labelTitle').val(),
            labelDetail: $('#labelDetail').val(),
            labelColor: $('#labelColor').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/label',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('라벨이 등록되었습니다.');
            window.location.href = '/admin';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

};

main.init();