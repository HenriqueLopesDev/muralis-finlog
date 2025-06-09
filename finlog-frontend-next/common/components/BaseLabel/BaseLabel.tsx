import React from 'react'

import { BaseLabelProps } from '@/common/components/BaseLabel/BaseLabelProps'

export function BaseLabel({
  htmlFor,
  title,
  requiredIndicator,
}: BaseLabelProps) {
  return (
    <label
      htmlFor={htmlFor}
      className="font-montserrat font-semibold text-[#636363]"
    >
      {title}: {requiredIndicator && <span>*</span>}
    </label>
  )
}
