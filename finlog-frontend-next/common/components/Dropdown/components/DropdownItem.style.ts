import { SxProps, Theme } from '@mui/material'

export const dropdownItemStyles: SxProps<Theme> = {
  fontWeight: 500,
  display: 'flex',
  alignItems: 'center',
  gap: '0.5rem',
  borderRadius: '6px',
  px: 2,
  py: 1,
  fontFamily: 'var(--font-poppins)',
  fontSize: '14px',
  color: '#565656',
  transition: 'all 200ms',
  '&:hover': {
    bgcolor: 'var(--color-02)',
    color: 'white',
  },
}
