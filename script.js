function searchJobPostings() {
    var keyword = $('#keyword').val();
    var location = $('#location').val();

    $.ajax({
        url: 'search.php',
        method: 'GET',
        data: {
            keyword: keyword,
            location: location
        },
        success: function(response) {
            $('#job-postings').html(response);
        },
        error: function() {
            alert('Erro ao buscar postagens de emprego.');
        }
    });

    return false;
}
