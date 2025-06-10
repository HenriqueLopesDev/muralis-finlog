'use client'

import Table from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableContainer from '@mui/material/TableContainer'
import TableHead from '@mui/material/TableHead'
import TableRow from '@mui/material/TableRow'
import Paper from '@mui/material/Paper'
import { Dropdown } from '@/common/components/Dropdown/Dropdown'
import MoreVertIcon from '@mui/icons-material/MoreVert'
import {
  StyledTableCell,
  StyledTableRow,
  TableContainerStyles,
} from './ExpensesTable.style'
import {
  formatToBrazilianCurrency,
  formatDateToBrazilianStandard,
} from '@/common/utils/formatters'
import { ExpensesTableProps } from './ExpensesTableProps'

export function ExpensesTable({
  rows,
  deleteExpenseModalDispatcher,
  updateExpenseModalDispatcher,
}: ExpensesTableProps) {
  return (
    <TableContainer component={Paper} sx={TableContainerStyles}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell align="center">Descrição</StyledTableCell>
            <StyledTableCell align="center">Valor</StyledTableCell>
            <StyledTableCell align="center">Tipo de pagamento</StyledTableCell>
            <StyledTableCell align="center">Categoria</StyledTableCell>
            <StyledTableCell align="center">Endereço</StyledTableCell>
            <StyledTableCell align="center">Data da compra</StyledTableCell>
            <StyledTableCell align="center">Ações</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.length === 0 && (
            <StyledTableRow>
              <StyledTableCell colSpan={7} align="center">
                <p className="text-[1rem] my-2">Nenhuma despesa encontrada.</p>
              </StyledTableCell>
            </StyledTableRow>
          )}
          {rows.map((row) => (
            <StyledTableRow key={row.id}>
              <StyledTableCell component="th" scope="row" align="center">
                {row.description}
              </StyledTableCell>
              <StyledTableCell align="center">
                {formatToBrazilianCurrency(row.value)}
              </StyledTableCell>
              <StyledTableCell align="center">
                {row.paymentType.type}
              </StyledTableCell>
              <StyledTableCell align="center">
                {row.category.name}
              </StyledTableCell>
              <StyledTableCell align="center">
                {`${row.address.street}, ${row.address.streetNumber}`}
              </StyledTableCell>
              <StyledTableCell align="center">
                {formatDateToBrazilianStandard(row.date)}
              </StyledTableCell>
              <StyledTableCell align="center">
                <Dropdown.Root
                  triggerContent={<MoreVertIcon sx={{ fontSize: '1rem' }} />}
                  menuPosition={{
                    vertical: 'top',
                    horizontal: 'left',
                  }}
                  menuAnimationDirection={{
                    vertical: 'top',
                    horizontal: 'right',
                  }}
                >
                  <Dropdown.Item
                    onClickCallback={() =>
                      updateExpenseModalDispatcher({
                        open: true,
                        expenseId: row.id,
                      })
                    }
                  >
                    Editar
                  </Dropdown.Item>
                  <Dropdown.Item
                    onClickCallback={() =>
                      deleteExpenseModalDispatcher({
                        expenseId: row.id,
                        open: true,
                      })
                    }
                  >
                    Excluir
                  </Dropdown.Item>
                </Dropdown.Root>
              </StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  )
}
