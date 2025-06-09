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
import { formatCurrency, formatDate } from '@/common/utils/masks'
import { ExpensesTableProps } from './ExpensesTableProps'

export function ExpensesTable({
  rows,
  deleteExpenseModalDispatcher,
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
          {rows.map((row) => (
            <StyledTableRow key={row.description}>
              <StyledTableCell component="th" scope="row" align="center">
                {row.description}
              </StyledTableCell>
              <StyledTableCell align="center">
                {formatCurrency(row.value)}
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
                {formatDate(row.date)}
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
                  <Dropdown.Item onClickCallback={() => null}>
                    Visualizar
                  </Dropdown.Item>
                  <Dropdown.Item onClickCallback={() => null}>
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
