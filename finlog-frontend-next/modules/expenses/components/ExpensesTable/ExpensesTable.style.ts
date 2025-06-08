import {
  styled,
  SxProps,
  TableCell,
  tableCellClasses,
  TableRow,
  Theme,
} from '@mui/material'

export const TableContainerStyles: SxProps<Theme> = {
  borderTopLeftRadius: 12,
  borderTopRightRadius: 12,
}

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
}))

export const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}))
