select *
from file_source
order by id desc

select fs.id,fs.name,acr.id,acr.row_of_doc,acr.operation_date
from file_source as fs
            join account_mov_raw as acr on fs.id=acr.source_file_id
where fs.id in (1499)
order by acr.id desc

select mov.operation_date,mov.concept,mov.amount,mov.total_amount,mov.total_amount_raw,movr.row_of_doc,movr.order_of_doc,fs.name,fs.id
from account_mov as mov
          join account_mov_raw as movr on movr.id=mov.original_id
          join file_source as fs on fs.id=movr.source_file_id
where mov.account_id=33
and mov.operation_date > '2014-12-01'

