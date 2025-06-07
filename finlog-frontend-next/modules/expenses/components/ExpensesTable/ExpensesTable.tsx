'use client'

import * as React from 'react'
import Table from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableContainer from '@mui/material/TableContainer'
import TableHead from '@mui/material/TableHead'
import TableRow from '@mui/material/TableRow'
import Paper from '@mui/material/Paper'
import { Dropdown } from '@/common/components/Dropdown/Dropdown'
import MoreVertIcon from '@mui/icons-material/MoreVert'
import { StyledTableCell, StyledTableRow } from './ExpensesTable.style'

function createExpense(
  description: string,
  amount: number,
  paymentType: string,
  category: string,
  address: string,
  purchaseDate: string,
) {
  return { description, amount, paymentType, category, address, purchaseDate }
}

const rows = [
  createExpense(
    'Youtube Premium',
    20,
    'Crédito',
    'Lazer',
    'Rua A, 123',
    '2023-10-01',
  ),
  createExpense('Spotify', 15, 'Débito', 'Música', 'Rua B, 456', '2023-10-02'),
  createExpense(
    'Netflix',
    30,
    'Crédito',
    'Entretenimento',
    'Rua C, 789',
    '2023-10-03',
  ),
  createExpense(
    'Amazon Prime',
    12,
    'Crédito',
    'Streaming',
    'Rua D, 101',
    '2023-10-04',
  ),
  createExpense(
    'HBO Max',
    18,
    'Débito',
    'Streaming',
    'Rua E, 202',
    '2023-10-05',
  ),
  createExpense(
    'Disney+',
    25,
    'Crédito',
    'Streaming',
    'Rua F, 303',
    '2023-10-06',
  ),
  createExpense(
    'Apple Music',
    10,
    'Débito',
    'Música',
    'Rua G, 404',
    '2023-10-07',
  ),
  createExpense(
    'Google Play Music',
    8,
    'Crédito',
    'Música',
    'Rua H, 505',
    '2023-10-08',
  ),
  createExpense('Tidal', 12, 'Débito', 'Música', 'Rua I, 606', '2023-10-09'),
  createExpense('Deezer', 10, 'Crédito', 'Música', 'Rua J, 707', '2023-10-10'),
]

export function ExpensesTable() {
  return (
    <TableContainer
      component={Paper}
      sx={{
        borderTopLeftRadius: 12,
        borderTopRightRadius: 12,
      }}
    >
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
              <StyledTableCell align="center">{row.amount}</StyledTableCell>
              <StyledTableCell align="center">
                {row.paymentType}
              </StyledTableCell>
              <StyledTableCell align="center">{row.category}</StyledTableCell>
              <StyledTableCell align="center">{row.address}</StyledTableCell>
              <StyledTableCell align="center">
                {row.purchaseDate}
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
                  <Dropdown.Item onClickCallback={() => null}>
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
