var eventRegistry = (function(){
    let xcsrfToken = "";
    const apiCall = function(connectInfo){
        let response = [];
        const headers = {};
        if(xcsrfToken){
            headers['x-csrf-token'] = xcsrfToken;
        }
        $.ajax({
            type: connectInfo.type,
            url: connectInfo.url,
            async: connectInfo.async,
            data: JSON.stringify(connectInfo.data),
            headers: headers,
            cache: false,
            contentType: "application/json; charset=utf-8",
            success: function(data, content, xhr) {
                if (!xcsrfToken) {
                    xcsrfToken = xhr.getResponseHeader('x-csrf-token');
                }
                response = data;
            },
            error: function(xhr, status, index, anchor) {
                //var errorMessage = that.getErrorResponse(xhr.responseText);
                if (!xcsrfToken) {
                    xcsrfToken = xhr.getResponseHeader('x-csrf-token');
                }
            }
        });
        return response;
    }
    return {
        readBooks : function(){
            const table = document.getElementsByClassName("table")[0];
            const response = apiCall({
                type:'GET',
                url: '/srv/list',
                async:false,
            });
            let rowsHtml = response.length > 0 ? "" : "<tr><td>no data</td></tr>";
            for(let book of response){
                rowsHtml += `<tr>
                    <td>${book.bookName}</td>
                    <td>${book.author}</td>
                    <td>${new Date(book.publishedOn).toDateString()}</td>
                    <td>${book.count}</td>
                </tr>`
            }
            table.tBodies[0].innerHTML = rowsHtml;
        }
    }
})();