import { styled, TableCell, tableCellClasses, TableRow } from '@mui/material'

export const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: 'var(--color-contrast-01)',
    color: theme.palette.common.white,
    padding: '14px 5px',
    fontWeight: 600,
    fontFamily: 'var(--font-poppins)',
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
    padding: '10px 5px',
    fontFamily: 'var(--font-poppins)',
  },
  '&:first-of-type': {
    borderTopLeftRadius: '12px',
  },
  '&:last-of-type': {
    borderTopRightRadius: '12px',
  },
}))

export const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}))
