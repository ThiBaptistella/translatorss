$(document).ready(function(){
  if ( $.fn.dataTable.isDataTable( '#datatables' ) ) {
    table = $('#datatables').DataTable();
}
else {
    table = $('#datatables').DataTable( {
        paging: false
    } );
}
    } );
