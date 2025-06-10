import React from 'react'

import { BaseSelectProps } from '@/common/components/BaseSelect/BaseSelectProps'

export function BaseSelect({ children, ...rest }: BaseSelectProps) {
  return (
    <select
      {...rest}
      className="
        h-12 rounded-[10px] border border-[#d2d2d2] p-[8px_10px]
        font-poppins text-sm outline-none transition
        focus:border-[var(--color-01)] focus:shadow-[0_3px_10px_0_rgba(34,41,47,0.1)]
        disabled:bg-[#d2d2d2] disabled:opacity-70 hover:cursor-pointer
      "
    >
      {children}
    </select>
  )
}
