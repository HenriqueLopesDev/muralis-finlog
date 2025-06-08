import { Box, CircularProgress } from '@mui/material'

export function LoadingSpinner() {
  return (
    <Box
      sx={{
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100vh',
        width: '100vw',
        position: 'fixed',
        backgroundColor: 'rgba(255, 255, 255, 0.8)',
        top: 0,
        left: 0,
      }}
    >
      <CircularProgress
        sx={{ color: 'var(--color-01)' }}
        size={60}
        thickness={4.5}
      />
    </Box>
  )
}
