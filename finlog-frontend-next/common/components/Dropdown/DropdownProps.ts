import React from 'react'
import { SxProps, Theme } from '@mui/material'

export interface MenuPositionProps {
  vertical: 'top' | 'center' | 'bottom'
  horizontal: 'left' | 'center' | 'right'
}

export interface DropdownRootProps {
  children: React.ReactNode
  menuPosition?: MenuPositionProps
  menuAnimationDirection?: MenuPositionProps
  ContainerStyles?: SxProps<Theme>
  triggerContent: React.ReactNode
  triggerStyles?: React.CSSProperties
}
