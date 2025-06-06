import { SxProps, Theme } from '@mui/material'

export const PaginationStyles: SxProps<Theme> = {
  '& .MuiPaginationItem-root': {
    backgroundColor: '#fbfbfb',
    border: '1px solid',
    borderColor: '#d2d2d2',
    width: '35px',
    height: '35px',
    borderRadius: '100%',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    transition: '0.3 all ease',
    '&:hover': {
      backgroundColor: 'var(--color-01)',
      color: 'white',
    },
    fontSize: '16px',
    fontWeight: 600,
    fontFamily: 'system-ui',
  },
  '& .Mui-selected': {
    backgroundColor: 'var(--color-01) !important',
    border: 'none',
    color: 'white',
  },
  '& .MuiPaginationItem-root.Mui-disabled': {
    backgroundColor: '#d2d2d2',
    border: 'none',
    color: '#494b4d',
    opacity: '65%',
  },
}
